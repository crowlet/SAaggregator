package pl.wat.wcy.panek.saaggregator.adapter.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.wat.wcy.panek.saaggregator.application.subject.SubjectAppService;
import pl.wat.wcy.panek.saaggregator.domain.subject.Subject;

import java.util.List;

@RequestMapping("subject")
@RestController
@RequiredArgsConstructor
public class SubjectRestController {

    private final SubjectAppService subjectAppService;

    @GetMapping("all")
    public List<Subject> allSubjects() {
        return subjectAppService.all();
    }
}
