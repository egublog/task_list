package jp.gihyo.profava.tasklist;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
public class HomeRestController {

  record TaskItem(String id, String task, String deadline, boolean done) {
  }

  private List<TaskItem> taskItems = new ArrayList<>();

  @RequestMapping("/home")
  String hello() {
    return """
      <html>
        <head>
          <title>Hello</title>
        </head>
        <body>
          <h1>Hello, Spring Boot!</h1>
          It works! <br>
          現在時刻は%sです。
        </body>
      </html>
        """.formatted(LocalDateTime.now());
  }

  @GetMapping("/add")
  String addItem(@RequestParam("task") String task,
      @RequestParam("deadline") String deadline) {
    String id = UUID.randomUUID().toString().substring(0, 8);
    TaskItem item = new TaskItem(id, task, deadline, false);
    taskItems.add(item);
    return "タスクを追加しました。";
  }

  @GetMapping("/list")
    String listItems() {
      String result = taskItems.stream()
          .map(TaskItem::toString)
          .collect(Collectors.joining(","));
          return result;
  }
}
