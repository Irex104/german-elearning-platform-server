package kiec.ireneusz.germanelearningplatformserver.domain.post.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import kiec.ireneusz.germanelearningplatformserver.domain.post.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostDTO {

    private Long id;
    private String headline;
    private String description;
    private String article;
    private String image;
    private String link;
    private boolean visible;

    public PostDTO(Post post) {
        this.id = post.getId();
        this.headline = post.getHeadline();
        this.description = post.getDescription();
        this.article = post.getArticle();
        this.image = post.getImage();
        this.link = post.getLink();
        this.visible = post.isVisible();
    }
}
