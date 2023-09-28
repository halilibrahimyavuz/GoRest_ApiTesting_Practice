package pages;


import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static io.restassured.RestAssured.given;

public class ApiValidation {

    public Response response;

    public Response postMethod(String name, String gender, String email, String status) {
       // updateBody de ıcı dolu bır body gelecek
        String requestBody = updateBody(name , gender ,email ,status);
        Response response =given()
                .headers("Authorization", "Bearer 104b4d1036cdb45868e51acab5843011b81ff25c5d3ac307d636317b880bc6dd" ,
                    "Content-Type", "application/json" ,
                            "Accept" , ContentType.JSON )
                .body(requestBody)  // body i requestte gonderdık gelen cevap response de
                .post();

        return response;


    }

    private String updateBody(String name, String gender, String email, String status) {
        //post put pacht gıbı farklı methdoalrda kullanmak ıcın ortak method yazdım
        //try catch yada throws kullanmamız lazım -
        // try catch --> mesaj alma avantajımız var
        //throws da bu methodu kulandıgım tüm methodlarda da throws kullanmam lazım kullanım zorlugu
        //hata nasıl handle edılır -throws ve trycatch farkları nedır  ?

        //{
        //  "id": null,
        //  "name": "replaceName",
        //  "email": "replaceEmail",
        //  "gender": "replaceGender",
        //  "status": "replaceStatus"
        //}
        String body = null;

        try {

            body=new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir")+ File.separator+"src/test/resources/data/userDetails.json")));
          // body nın ıcındekılerı verıdgım parametreler ıle doldurup kendıme bır json data olusturuyor
            body=body.replaceAll("replaceName", name);
            body=body.replaceAll("replaceEmail", email);
            body=body.replaceAll("replaceGender", gender);
            body=body.replaceAll("replaceStatus", status);
        } catch (IOException e) {
            //sadece IOexception da esaj verır
            e.printStackTrace();
            System.out.println(e.getMessage());
            //hata mesajını yazdıermak ıcın kullandık
        }
        //methodu kullanınca bana body gonderecek
        return body;
    }




}
