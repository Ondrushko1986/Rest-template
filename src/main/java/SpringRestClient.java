import Model.User;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;


import java.util.Collections;

public class SpringRestClient {

    private static final String URL_USERS = "http://91.241.64.178:7081/api/users";
    private static final String DELETE_URL_USER = "http://91.241.64.178:7081/api/users/3";
    private static final String COOKIE_NAME = "Cookie";
    private static final String COOKIE_VALUE = "JSESSIONID=73D7B37F9583EF3BF1FBBBD1DC5F6A7E; Path=/; HttpOnly";

    private static final RestTemplate restTemplate = new RestTemplate();



    private void getUsers() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>("params", headers);
        ResponseEntity<String> result = restTemplate.exchange(URL_USERS, HttpMethod.GET, entity,
                String.class);
        System.out.println(result);
    }

    private void createUser() {
        HttpHeaders headers = new HttpHeaders();
        headers.add(COOKIE_NAME, COOKIE_VALUE);
        User newUser = new User(3L, "James", "Brown", (byte) 12);
        HttpEntity<User> requestBody = new HttpEntity<>(newUser,headers);
        ResponseEntity<User> result = restTemplate.postForEntity(URL_USERS, requestBody, User.class);
        System.out.println("Status code:" + result.getStatusCode());

        if (result.getStatusCode() == HttpStatus.OK) {
            User user = result.getBody();
            assert user != null;
            System.out.println("(Client Side) User is created: "+ user.getId());
        }
    }

//    private void updateUser() {
//        HttpHeaders headers = new HttpHeaders();
//        headers.add(COOKIE_NAME, COOKIE_VALUE);
//        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//        User updatedUser = new User(3L, "Thomas", "Shelby", (byte) 55);
//        RestTemplate restTemplate = new RestTemplate();
//        HttpEntity<User> requestBody = new HttpEntity<>(updatedUser, headers);
//        restTemplate.put(URL_USERS, requestBody, User.class);
//    }
//
//    private void deleteUser() {
//        HttpHeaders headers = new HttpHeaders();
//        headers.add(COOKIE_NAME, COOKIE_VALUE);
//        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//        RestTemplate restTemplate = new RestTemplate();
//        HttpEntity<String> requestBody = new HttpEntity<>("params", headers);
//        restTemplate.delete(DELETE_URL_USER, requestBody);
//    }

    public static void main(String[] args) {

        SpringRestClient springRestClient = new SpringRestClient();

        springRestClient.getUsers();

        springRestClient.createUser();

//        springRestClient.updateUser();
//
//        springRestClient.deleteUser();
    }


}
