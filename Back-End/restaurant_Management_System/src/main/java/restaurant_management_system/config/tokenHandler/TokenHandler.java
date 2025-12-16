package restaurant_management_system.config.tokenHandler;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import org.springframework.stereotype.Component;
import restaurant_management_system.dto.UsersDto;
import restaurant_management_system.helper.JwtToken;
import restaurant_management_system.service.UserService;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class TokenHandler {


   private UserService userService;


    String secretKey;
    Duration time;

    JwtBuilder jwtBuilder;
    JwtParser jwtParser;


// TODO _________________________HandelToken_________________________
// TODO _____________________________________________________________
    public TokenHandler(JwtToken jwtToken, UserService userService)
    {
        this.userService = userService;
        this.secretKey = jwtToken.getSecret();
        this.time = jwtToken.getTime();

        Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        jwtBuilder = Jwts.builder().signWith(key);
        jwtParser = Jwts.parserBuilder().setSigningKey(key).build();
    }


// TODO _________________________createToken_________________________
// TODO _____________________________________________________________
    public String createToken(UsersDto usersDto)
    {
        List<String> roleNames = usersDto.getRoles().stream()
                .map(role -> role.getCode().toString())
                .collect(Collectors.toList());



        Date issuDate = new Date();
        Date epireDate = Date.from(issuDate.toInstant().plus(time));

        return jwtBuilder.setSubject(usersDto.getUsername()).claim("roles" ,roleNames).setIssuedAt(issuDate).setExpiration(epireDate).compact();
    }



// TODO _________________________validateToken_________________________
// TODO _____________________________________________________________
    public UsersDto validateToken(String token)
    {
      try{

          if(!jwtParser.isSigned(token))
          {
              return null;
          }

          Claims claims = jwtParser.parseClaimsJws(token).getBody();

          Date issuedDate = claims.getIssuedAt();
          Date epireDate = claims.getExpiration();
          String username = claims.getSubject();

          UsersDto usersDto = userService.getUserByUsername(username);


          return !(usersDto != null && issuedDate.before(epireDate) && epireDate.after(new Date()))?null:usersDto;

      }catch (Exception e)
      {
          return null;
      }
    }



}
