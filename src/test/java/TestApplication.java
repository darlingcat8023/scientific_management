import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.AnnotatedParameter;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.lang.reflect.RecordComponent;
import java.util.Map;

/**
 * @author liujiajun
 * @date 3/31/22
 */
//@SpringBootTest
public class TestApplication {

    @Test
    @SneakyThrows
    public void test() {
        ObjectMapper mapper = new ObjectMapper();
        JacksonAnnotationIntrospector implicitRecordAI = new JacksonAnnotationIntrospector() {
            @Override
            public PropertyName findNameForDeserialization(Annotated a) {
                PropertyName nameForDeserialization = super.findNameForDeserialization(a);
                if (PropertyName.USE_DEFAULT.equals(nameForDeserialization) && a instanceof AnnotatedParameter && ((AnnotatedParameter) a).getDeclaringClass().isRecord()) {
                    String str = findImplicitPropertyName((AnnotatedParameter) a);
                    if (str != null && !str.isEmpty()) {
                        return PropertyName.construct(str);
                    }
                }
                return nameForDeserialization;
            }

            @Override
            public String findImplicitPropertyName(AnnotatedMember m) {
                if (m.getDeclaringClass().isRecord()) {
                    if (m instanceof AnnotatedParameter parameter) {
                        return m.getDeclaringClass().getRecordComponents()[parameter.getIndex()].getName();
                    }
                    for (RecordComponent recordComponent : m.getDeclaringClass().getRecordComponents()) {
                        if (recordComponent.getName().equals(m.getName())) {
                            return m.getName();
                        }
                    }
                }
                return super.findImplicitPropertyName(m);
            }
        };
    }

    @Test
    public void tokenTest() {
        Map<String, Object> tokenMap = Map.of("userId",1, "userName","name");
        String token = Jwts.builder().setSubject("user")
                .setClaims(tokenMap)
                .signWith(SignatureAlgorithm.HS256, "user").compact();
        System.out.println(token);
        Claims user = Jwts.parser().setSigningKey("user").parseClaimsJws(token).getBody();
        System.out.println(user.get("userId"));
    }

}
