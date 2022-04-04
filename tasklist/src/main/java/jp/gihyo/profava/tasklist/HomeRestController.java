package jp.gihyo.profava.tasklist;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;

@RestController
public class HomeRestController {
  @RequestMapping("/home")
  String hello() {
    return """
        Hello.
        It works!
        現在時刻は%sです。
        """.formatted(LocalDateTime.now());
  }
}
