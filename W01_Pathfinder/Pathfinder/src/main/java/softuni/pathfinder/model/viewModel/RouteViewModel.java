package softuni.pathfinder.model.viewModel;

import softuni.pathfinder.model.entity.enums.LevelEnum;

public class RouteViewModel {
    private Long id;
    private LevelEnum level;
    private String name;
    private String pictureUrl;
    private String description;


    public RouteViewModel() {
    }

    public Long getId() {
        return id;
    }

    public RouteViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public LevelEnum getLevel() {
        return level;
    }

    public RouteViewModel setLevel(LevelEnum level) {
        this.level = level;
        return this;
    }

    public String getName() {
        return name;
    }

    public RouteViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public RouteViewModel setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public RouteViewModel setDescription(String description) {
        this.description = description;
        return this;
    }
}
