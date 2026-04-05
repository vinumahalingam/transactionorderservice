package com.transactionorder.transactionorderservice.entity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@Document(collection = "ZelleToken")
public class ZelleToken {
    @Id
    private String id;
    @Field("tokenType")
    private String tokenType;
    private String tokenValue;
    private Long customerProfileId;
}
