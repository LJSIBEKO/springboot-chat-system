package linda.co.za.chat.system.model.chats;

import jakarta.persistence.*;
import linda.co.za.chat.system.model.BaseEntity;
import linda.co.za.chat.system.model.message.Message;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "conversation")
public class Conversation extends BaseEntity
{
    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "conversation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Member> members;

    @OneToMany(mappedBy = "conversation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Message> messages;
}
