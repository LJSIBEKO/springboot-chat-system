package linda.co.za.chat.system.model.message;

import jakarta.persistence.*;
import linda.co.za.chat.system.model.BaseEntity;
import linda.co.za.chat.system.model.account.Account;
import linda.co.za.chat.system.model.chats.Conversation;
import lombok.*;


import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "messages")
public class Message extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "conversation_id", nullable = false)
    private Conversation conversation;

    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private Account sender;



    @Column(nullable = false)
    private boolean isRead;

    private LocalDateTime readAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MessageType type;

    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToOne
    @JoinColumn(name = "reply_to_id")
    private Message replyTo;

    private boolean deleted;
}
