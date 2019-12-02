package xyz.crowxx.dcxtapi.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/food")
public class IndexController {
    @Value("${com.gzy.im.service.FileSystemStorageService.rootLocationStr}")
    String path;
    @GetMapping("/index")
    Object findIndexImages(){
        String indexImagesPath = path + "/default";
        indexImagesPath.replace('/','\\');
        String json = "[\n" +
                " {\n" +
                "\n" +
                "    \"imgUrls\": [{\n" +
                "      \"id\": 1,\n" +
                "      \"src\": \"../../images/banner_1.png\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 2,\n" +
                "      \"src\": \"../../images/banner_2.png\"\n" +
                "\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 3,\n" +
                "      \"src\": \"../../images/banner_3.png\"\n" +
                "    }],\n" +
                "    \"image_ad\":\"../../images/image_ad.png\",\n" +
                "    \"image_bottom\":[{\n" +
                "      \"id\": 1,\n" +
                "      \"src\":\"../../images/bottom_1.png\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": 2,\n" +
                "      \"src\":\"../../images/bottom_2.png\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": 3,\n" +
                "       \"src\": \"../../images/bottom_3.png\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": 4,\n" +
                "        \"src\":\"../../images/bottom_1.png\"\n" +
                "      }]\n" +
                "  }\n" +
                "]";
        return json;
    }


}
