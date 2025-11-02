package itmo.ib.service;

import itmo.ib.dto.ChapterDto;
import itmo.ib.dto.CreateChapterRequest;
import itmo.ib.model.Chapter;
import itmo.ib.repository.ChapterRepository;
import itmo.ib.util.XssUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChapterService {

    private final ChapterRepository chapterRepository;

    @Transactional
    public List<ChapterDto> findAll() {
        return chapterRepository.findAll().stream()
                .map(this::toDto)
                .toList();
    }

    @Transactional
    public ChapterDto create(CreateChapterRequest request) {
        var chapter = Chapter.builder()
                .name(XssUtils.sanitize(request.name()))
                .build();
        var saved = chapterRepository.save(chapter);
        return toDto(saved);
    }

    private ChapterDto toDto(Chapter chapter) {
        return new ChapterDto(chapter.getId(), chapter.getName());
    }
}
