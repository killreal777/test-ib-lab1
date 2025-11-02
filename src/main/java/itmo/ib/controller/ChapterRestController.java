package itmo.ib.controller;

import itmo.ib.dto.ChapterDto;
import itmo.ib.dto.CreateChapterRequest;
import itmo.ib.service.ChapterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/data")
@RequiredArgsConstructor
public class ChapterRestController {

    private final ChapterService chapterService;

    @GetMapping
    public ResponseEntity<List<ChapterDto>> findAll() {
        return ResponseEntity.ok(chapterService.findAll());
    }

    @PostMapping
    public ResponseEntity<ChapterDto> create(@RequestBody CreateChapterRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(chapterService.create(request));
    }
}
