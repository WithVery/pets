package group.cards.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {

    @Value("${jwt-secret}")
    private String secretKey;

    @Value("${jwt-expiration}")
    private long jwtExpiration;

    //Внутренние методы

    private SecretKey getSigningKey() {

        //Превращение секретного ключа в объект SecretKey, которым
        //jjwt (java json web token) подписывает и проверяет токены
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);

        //Возвращаем ключ для HMAC-SHA256 подписи
        return Keys.hmacShaKeyFor(keyBytes);
    }

    //Разбирает токен, проверяет его подпись и достаёт из него все claims (payload)
    //Если подпись не совпадает или формат токена не правильный - выбрасывает исключение
    //ЭТО ЕДИНСТВЕННОЕ МЕСТО ГДЕ ПРОИСХОДИТ КРИПТОГРАФИЧЕСКАЯ ПРОВЕРКА ТОКЕНА
    private Claims extractAllClaims(String token){
        return Jwts.parser()
                .verifyWith(getSigningKey()) //проверяем секретный ключ
                .build()
                .parseSignedClaims(token) // !парсим токен и проверяем подпись!
                .getPayload(); // получаем payload
    }

    //Проверяет: истёк ли токен
    private boolean isTokenExpired(String token){

        //extractAllClaims(token).getExpiration() - возвращает Date, извлечённый из claim'а
        // exp токена. (Время когда токен должен стать невалидным)
        //.before(new Date()) - возвращает true, если вызывающий объект раньше, чем new Date()
        // Если проще, то - «Дата истечения токена раньше, чем сейчас?» если да, значит срок уже прошёл, токен просрочен.
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    //Публичные методы

    //Достаёт username из claim'а токена (claim "sub" -> subject)
    //Используется фильтром, чтобы понять, за кого выдавать себя запросу, ещё до проверки токена.
    public String extractUsername(String token){
        return extractAllClaims(token).getSubject();
    }

    //Генерирует jwt токен подписанный секретным ключом сервера
    //В payload кладутся: subject, время создания, время истечения
    //и список ролей - это позволит фильтру восстановить права пользователя без обращения к бд.
    //Принимает extractClaims - доп произвольные данные для payload
    //UserDetails - пользователь для которого создаётся токен
    public String generateToken(Map<String, Objects> extractClaims, UserDetails userDetails){
        return Jwts.builder()
                //Добавление в payload кастомных claims
                .claims(extractClaims)
                //Установка claim "sub" - что-то вроде идентификатора пользователя
                .subject(userDetails.getUsername())
                //Установка "iat" - время создания токена
                .issuedAt(new Date())
                //Установка "exp"- время истечения токена
                .expiration(new Date(System.currentTimeMillis() + jwtExpiration))
                //Добавляем кастомный claim "roles" со списком прав пользователя
                .claim("roles", userDetails.getAuthorities().stream()
                        //Достаём строковое название для каждой роли, например: "ROLE_USER"
                        .map(GrantedAuthority::getAuthority)
                        //собираем все роли в обычный List<String>
                        .collect(Collectors.toList()))
                //Подписываем токен секретным ключом
                .signWith(getSigningKey())
                //Собираем всё в финальную строку
                .compact();
    }

    //Генерирует новый jwt токен, без дополнительных claims
    //По сути обёртка над методом выше
    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(), userDetails);
    }

    //Проверяем что токен принадлежит именно этому пользователю и срок не истёк
    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractUsername(token); //получаем username из userDetails

        //Проверяем пользователя и токен на истечения срока
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }
}