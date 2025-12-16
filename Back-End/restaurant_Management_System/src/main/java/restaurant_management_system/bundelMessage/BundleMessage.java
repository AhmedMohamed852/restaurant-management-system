package restaurant_management_system.bundelMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;

public class BundleMessage {


    @Value("${spring.messages.basename}")
    String baseName;

@Bean
public ResourceBundleMessageSource getMessageSource(){

    ResourceBundleMessageSource source = new ResourceBundleMessageSource();

    source.setBasename(baseName);
    source.setDefaultEncoding("UTF-8");
    return source;
}
}
