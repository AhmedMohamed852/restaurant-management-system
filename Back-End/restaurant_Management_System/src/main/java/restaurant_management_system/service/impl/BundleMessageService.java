package restaurant_management_system.service.impl;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;
import restaurant_management_system.helper.MessageResponse;

import java.util.Locale;

@Component
@NoArgsConstructor
public class BundleMessageService {

    private  ResourceBundleMessageSource messageSource;

    @Autowired
    public BundleMessageService(ResourceBundleMessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getMessage_ar(String key){
        return messageSource.getMessage(key,null,new Locale("ar"));
    }

    public String getMessage_en(String key){
        return messageSource.getMessage(key,null,new Locale("en"));
    }

    public MessageResponse getMessage(String key){
        return new MessageResponse(getMessage_en(key),getMessage_ar(key));
    }
}
