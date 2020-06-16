package kiec.ireneusz.germanelearningplatformserver.domain.post;

import kiec.ireneusz.germanelearningplatformserver.domain.post.dto.PostApi;
import kiec.ireneusz.germanelearningplatformserver.utils.AbstractModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(schema = "public", name="posts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Post extends AbstractModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String headline;
    @NotNull
    private String description;
    private String article;
    private String image;
    private String link;
    private boolean visible;

    public Post(PostApi api) {
        this.headline = api.getHeadline();
        this.description = api.getDescription();
        this.article = api.getArticle();
        this.image = api.getImage();
        this.link = api.getLink();
        this.visible = api.isVisible();
    }

    public void update(PostApi api){
        this.headline = api.getHeadline();
        this.description = api.getDescription();
        this.article = api.getArticle();
        this.image = api.getImage();
        this.link = api.getLink();
        this.visible = api.isVisible();
    }

}
