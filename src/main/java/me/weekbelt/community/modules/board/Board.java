package me.weekbelt.community.modules.board;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.weekbelt.community.modules.account.Account;
import me.weekbelt.community.modules.board.form.BoardUpdateForm;
import me.weekbelt.community.modules.reply.Reply;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter @NoArgsConstructor @EqualsAndHashCode(of = "id")
@Entity
public class Board {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 200, nullable = false)
    private String title;

    @Lob
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @OneToMany(mappedBy = "board")
    private List<Reply> replyList = new ArrayList<>();

    private Integer viewCount = 0;

    @Enumerated(EnumType.STRING)
    private BoardType boardType;

    private LocalDateTime createdDateTime;
    private LocalDateTime modifiedDateTime;

    @Builder
    public Board(String title, String content, Account account, BoardType boardType,
                 LocalDateTime createdDateTime, LocalDateTime modifiedDateTime) {
        this.title = title;
        this.content = content;
        this.account = account;
        this.boardType = boardType;
        this.createdDateTime = createdDateTime;
        this.modifiedDateTime = modifiedDateTime;
    }

    public void plusViewCount() {
        viewCount++;
    }

    public void update(BoardUpdateForm boardUpdateForm) {
        this.title = boardUpdateForm.getTitle();
        this.content = boardUpdateForm.getContent();
        this.boardType = boardUpdateForm.getBoardType();
    }
}
