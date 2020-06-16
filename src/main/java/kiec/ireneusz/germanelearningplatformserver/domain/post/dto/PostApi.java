package kiec.ireneusz.germanelearningplatformserver.domain.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostApi {

    private String headline;
    private String description;
    private String article;
    private String image;
    private String link;
    private boolean visible = true;

}
