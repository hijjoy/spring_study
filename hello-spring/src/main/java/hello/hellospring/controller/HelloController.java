package hello.hellospring.controller;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello") // get요청 8080/hello
    public String hello(Model model) {
        model.addAttribute("data","hello!!");
        return "hello"; // 기본적으로 resources:templates/hello.html에 가서 랜더링
    }

    @GetMapping("hello-mvc") // 8080/hello-mvc?name=여기에 name 값, command+p 눌러서 옵션 추가가능 (지금 name은 required = true)
    public String helloMvc(@RequestParam(value = "name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody // return 내용을 그대로 보내주겠다!! -> viewResolver를 사용하지 않음
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }


    // JSON 형태로 결과 반환
    @GetMapping("hello-api")
    @ResponseBody // @ResponseBody가 있으면 HttpMessageConverter가 동작, return이 객체인 경우 MappingJackson2HttpMessageConverter가 json방식으로 변환하여 반환
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello {
        private String name;

        // getter setter 메소드 (단축키 : control + enter)
        // name은 private -> 외부에서 직접적으로 접근하는 것을 막음
        // 메소드를 통해 데이터에 접근하도록 유도 (메소드는 매개값을 검증해서 유효한 값만 데이터로 저장할 수 있기 때문)
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


}
