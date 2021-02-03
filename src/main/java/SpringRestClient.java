import Model.User;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;


public class SpringRestClient {


    private void getAllResults() {
        //Get Users
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> result = restTemplate.getForEntity("http://91.241.64.178:7081/api/users", String.class);
        String cookieFromResponse = result.getHeaders().getFirst(HttpHeaders.SET_COOKIE);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cookie", cookieFromResponse);
        headers.setContentType(MediaType.APPLICATION_JSON);
        System.out.println(result);

        // Create user
        User newUser = new User(3L, "James", "Brown", (byte) 12);
        HttpEntity<User> requestBody = new HttpEntity<>(newUser, headers);
        ResponseEntity<String> result2 = restTemplate.postForEntity("http://91.241.64.178:7081/api/users", requestBody, String.class);
        System.out.println(result2);

        // Update user
        User updatedUser = new User(3L, "Thomas", "Shelby", (byte) 55);
        HttpEntity<User> userHttpEntity = new HttpEntity<>(updatedUser, headers);
        ResponseEntity<String> result3 = restTemplate.exchange("http://91.241.64.178:7081/api/users", HttpMethod.PUT, userHttpEntity, String.class);
        System.out.println(result3);

        // Delete user
        HttpEntity<String> requestBody2 = new HttpEntity<>(null, headers);
        ResponseEntity<String> result4 = restTemplate.exchange("http://91.241.64.178:7081/api/users/3", HttpMethod.DELETE, requestBody2, String.class);
        System.out.println(result4);

    }


    public static void main(String[] args) {

        SpringRestClient springRestClient = new SpringRestClient();

        springRestClient.getAllResults();

    }
}
