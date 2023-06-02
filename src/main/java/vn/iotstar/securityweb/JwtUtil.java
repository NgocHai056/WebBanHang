package vn.iotstar.securityweb;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import vn.iotstar.model.UserModel;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

public class JwtUtil {
	// Chữ kí dùng để xác minh tính toàn vẹn của token
    private static final String SECRET_KEY = "secret_key_web_ban_hang"; 

    // Hàm khởi tạo token
    public static String generateToken(String subject, Integer userId) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + 86400000); // Set hiệu lực của token là 24 hours
        
        // Băm chữ kí với thuật toán SHA 256
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, SignatureAlgorithm.HS256.getJcaName());
 
        return Jwts.builder()
                .setSubject(subject)
                .claim("userId", userId)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, signingKey)
                .compact();
    }

    // Hàm xác thực token
    public static boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey((DatatypeConverter.parseBase64Binary(SECRET_KEY))).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            // Token không hợp lệ hoặc hết hạn
            return false;
        }
    }

    // Hàm lấy thông tin user từ token
    public static UserModel getUserFromToken(String token) {
    	final Claims claims = decodeJWT(token);
    	UserModel user = new UserModel();
    	user.setUsers_username(claims.getSubject());
    	user.setUserID( (int)claims.get("userId"));
    	return user;
    }

    public static String extractSubject(String token) {
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey((DatatypeConverter.parseBase64Binary(SECRET_KEY))).parseClaimsJws(token);
        return claimsJws.getBody().getSubject();
    }
    

    
    private static Claims decodeJWT(String jwt) {
        // This line will throw an exception if it is not a signed JWS (as expected)
        return Jwts.parser() // Configured and then used to parse JWT strings
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY)) // Sets the signing key used to verify
                                                                                // any discovered JWS digital signature
                .parseClaimsJws(jwt) // Parses the specified compact serialized JWS string based
                .getBody();
    }
}
