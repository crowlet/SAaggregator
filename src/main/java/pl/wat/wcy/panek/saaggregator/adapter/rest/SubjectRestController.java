package pl.wat.wcy.panek.saaggregator.adapter.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.wat.wcy.panek.saaggregator.domain.subject.Subject;
import pl.wat.wcy.panek.saaggregator.domain.subject.SubjectDomainService;

import java.util.List;

@RequestMapping("subject")
@RestController
@RequiredArgsConstructor
public class SubjectRestController {

    private final  SubjectDomainService subjectDomainService;

    @GetMapping("all")
    public List<Subject> allSubjects() {
        return subjectDomainService.allSubjects();
    }
}
