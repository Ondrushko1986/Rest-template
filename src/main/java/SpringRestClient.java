import Model.User;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;


import java.util.Collections;

public class SpringRestClient {

    private static final String URL_USERS = "http://91.241.64.178:7081/api/users";
    private static final String DELETE_URL_USER = "http://91.241.64.178:7081/api/users/3";
    private static final String HEADER_NAME = "Cookie";
    private static final String HEADER_VALUE = "JSESSIONID=56EDEC71D73041FD4C99D423D4CFF46F; Path=/; HttpOnly";

    private static final RestTemplate restTemplate = new RestTemplate();
    private static final HttpHeaders headers = new HttpHeaders();

    private void getUsers() {
        headers.add(HEADER_NAME, HEADER_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>("params", headers);
        ResponseEntity<String> result = restTemplate.exchange(URL_USERS, HttpMethod.GET, entity,
                String.class);
        System.out.println(result);
    }

    private void createUser() {
        User newUser = new User(3L, "James", "Brown", (byte) 12);
        HttpEntity<User> requestBody = new HttpEntity<>(newUser,headers);
        ResponseEntity<String> result = restTemplate.exchange(URL_USERS, HttpMethod.POST, requestBody, String.class);
        System.out.println(result);

    }

    private void updateUser() {
        User updatedUser = new User(3L, "Thomas", "Shelby", (byte) 55);
        HttpEntity<User> requestBody = new HttpEntity<>(updatedUser, headers);
        ResponseEntity<String> result = restTemplate.exchange(URL_USERS, HttpMethod.PUT, requestBody, String.class);
        System.out.println(result);
    }

    private void deleteUser() {
        headers.add(HEADER_NAME, HEADER_VALUE);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> requestBody = new HttpEntity<>("params", headers);
        restTemplate.exchange(DELETE_URL_USER, HttpMethod.DELETE, requestBody, String.class);
    }

    public static void main(String[] args) {

        SpringRestClient springRestClient = new SpringRestClient();

        springRestClient.getUsers();

        springRestClient.createUser();

        springRestClient.updateUser();

        springRestClient.deleteUser();
    }


}
