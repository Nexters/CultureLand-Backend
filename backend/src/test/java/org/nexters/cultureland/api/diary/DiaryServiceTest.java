package org.nexters.cultureland.api.diary;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DiaryServiceTest {
/*
    @Autowired
    private DiaryService diaryService;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private DiaryRepository diaryRepository;
    @MockBean
    private ModelMapper modelMapper;

    @Test
    void 기록_수정_테스트() {
        Diary testDiary = Diary.builder()
                .id(1L)
                .title("title")
                .content("content")
                .sometime(LocalDateTime.now())
                .place("강남역 메리츠타워")
                .withWho("컬쳐랜드")
                .build();

        given(diaryRepository.findById(1L)).willReturn(Optional.ofNullable(testDiary));
        when(diaryRepository.save(any())).thenReturn(testDiary);

        // when
        String updateTitle = "제목";
        String updateContent = "내용";
        LocalDateTime updateWhen = LocalDateTime.parse("2019-08-03T00:00:00");
        String updateWhere = "공덕역 창업센터";
        String updateWith = "넥스터즈";
        userRepository.getOne(1234567L);
        Diary updateDiary = diaryService.updateDiaryOf(1234567L, new DiaryDto(updateTitle, updateWhen, updateWhere, updateWith, updateContent));

        // then
        assertThat(updateDiary.getTitle()).isEqualTo(updateTitle);
        assertThat(updateDiary.getContent()).isEqualTo(updateContent);
        assertThat(updateDiary.getSometime()).isEqualTo(updateWhen);
        assertThat(updateDiary.getPlace()).isEqualTo(updateWhere);
        assertThat(updateDiary.getWithWho()).isEqualTo(updateWith);
    }

    @Test
    public void modelMapperTest(){
        DiaryDto diaryDto = DiaryDto.builder()
                .content("content")
                .title("title")
                .sometime(LocalDateTime.now())
                .place("where")
                .withWho("Who")
                .build();
        Diary diary = modelMapper.map(diaryDto, Diary.class );
        System.out.println(diary.toString());
    }*/
}
