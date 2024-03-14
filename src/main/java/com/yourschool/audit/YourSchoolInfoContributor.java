package com.yourschool.audit;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class YourSchoolInfoContributor implements InfoContributor {

    @Override
    public void contribute(Info.Builder builder) {
        Map<String, String> yourMap = new HashMap<String, String>();
        yourMap.put("App Name", "YourSchool");
        yourMap.put("App Description", "Your School Web Application for Students and Admin");
        yourMap.put("App Version", "1.0.0");
        yourMap.put("Contact Email", "info@yourschool.com");
        yourMap.put("Contact Mobile", "+1(21) 673 4587");
        builder.withDetail("yourschool-info", yourMap);
    }

}
