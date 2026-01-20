package com.example.galleryback.repository;

import com.example.galleryback.entity.Photo;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestConstructor;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@RequiredArgsConstructor
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class PhotoRepositoryTest {

    private final PhotoRepository photoRepository;

    @Test
    @DisplayName("사진 저장 성공")
    void save() {
        // given
        Photo photo = Photo.builder()
                .title("test image")
                .description("test content")
                .imageUrl("/uploads/test.jpg")
                .build();

        // when
        Photo saved = photoRepository.save(photo);

        // then
        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getTitle()).isEqualTo("test image");
    }

    @Test
    @DisplayName("사진 목록 조회")
    void findAll() {
        // given
        photoRepository.save(
                Photo.builder()
                        .title("test image")
                        .description("test content")
                        .imageUrl("/uploads/test.jpg")
                        .build()
        );

        photoRepository.save(
                Photo.builder()
                        .title("test image2")
                        .description("test content")
                        .imageUrl("/uploads/test2.jpg")
                        .build()
        );

        // when
        List<Photo> photos = photoRepository.findAll();

        // then
        assertThat(photos).hasSize(2);
    }
}
