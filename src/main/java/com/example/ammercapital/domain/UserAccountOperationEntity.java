package com.example.ammercapital.domain;

import com.example.ammercapital.dicts.OperationType;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_accounts_operations")
public class UserAccountOperationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private BigDecimal change;
    private Long accountId;
    @Enumerated(EnumType.STRING)
    private OperationType operationType;
    @CreationTimestamp
    private LocalDateTime createDateTime;

    public UserAccountOperationEntity(BigDecimal change, Long accountId, OperationType operationType) {
        this.change = change;
        this.accountId = accountId;
        this.operationType = operationType;
    }

    public UserAccountOperationEntity() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getChange() {
        return change;
    }

    public void setChange(BigDecimal change) {
        this.change = change;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long userAccount) {
        this.accountId = userAccount;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(LocalDateTime createDateTime) {
        this.createDateTime = createDateTime;
    }
}
