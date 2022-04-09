package com.personal.cl.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.AnnotatedParameter;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import org.hibernate.validator.HibernateValidator;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;

import javax.validation.Validation;

@EnableWebFlux
@Configuration(proxyBeanMethods = false)
public class WebFluxConfiguration implements WebFluxConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedHeaders("*")
                .allowedMethods("GET", "POST", "OPTIONS", "DELETE", "PUT");
        WebFluxConfigurer.super.addCorsMappings(registry);
    }

    @Override
    public Validator getValidator() {
        var validatorFactory = Validation.byProvider(HibernateValidator.class)
                .configure()
                .messageInterpolator(new ParameterMessageInterpolator())
                .addProperty( "hibernate.validator.fail_fast", "true" )
                .buildValidatorFactory();
        return new SpringValidatorAdapter(validatorFactory.getValidator());
    }

    @Override
    public void configureHttpMessageCodecs(ServerCodecConfigurer configurer) {
        // 配置jackson
        var objectMapper = new ObjectMapper();
        var implicitRecordAI = new JacksonAnnotationIntrospector() {
            @Override
            public PropertyName findNameForDeserialization(Annotated a) {
                var nameForDeserialization = super.findNameForDeserialization(a);
                if (PropertyName.USE_DEFAULT.equals(nameForDeserialization) && a instanceof AnnotatedParameter && ((AnnotatedParameter) a).getDeclaringClass().isRecord()) {
                    var str = findImplicitPropertyName((AnnotatedParameter) a);
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
                    for (var recordComponent : m.getDeclaringClass().getRecordComponents()) {
                        if (recordComponent.getName().equals(m.getName())) {
                            return m.getName();
                        }
                    }
                }
                return super.findImplicitPropertyName(m);
            }
        };
        // jackson支持record类型序列化和反序列化
        objectMapper.setAnnotationIntrospector(implicitRecordAI);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        configurer.defaultCodecs().jackson2JsonEncoder(new Jackson2JsonEncoder(objectMapper));
        configurer.defaultCodecs().jackson2JsonDecoder(new Jackson2JsonDecoder(objectMapper));
        WebFluxConfigurer.super.configureHttpMessageCodecs(configurer);
    }

}
