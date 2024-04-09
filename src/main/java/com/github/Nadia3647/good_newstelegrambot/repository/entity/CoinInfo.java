package com.github.Nadia3647.good_newstelegrambot.repository.entity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.ArrayList;
import java.util.List;
import static java.util.Objects.isNull;

@Data
@Entity
@Table(name = "coin_info")

@EqualsAndHashCode
public class CoinInfo {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "nominal")
    private Integer nominal;

    @Column(name = "year")
    private Integer year;

    @Column(name = "mint")
    private String mint;

    @Column(name = "price")
    private Integer price;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "group_x_user",
            joinColumns = @JoinColumn(name = "coin_info_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )

    private List<TelegramUser> users;

    public void addUser(TelegramUser telegramUser) {
        if (isNull(users)) {
            users = new ArrayList<>();
        }
        if (users.stream().noneMatch(it -> it.getChatId().equals(telegramUser.getChatId()))) {
            users.add(telegramUser);
        }
    }

}
