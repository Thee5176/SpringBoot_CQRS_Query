package com.thee5176.record.springboot_cqrs_query.Domain.model;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


/**
 * 取引： 取引に少なくとも2つの科目が関係し、一つが借方 他のは貸方
 * 
 * ttps://www.geeksforgeeks.org/accountancy/accounting-entry-meaning-types-advantages-examples/

 */
@Entity
@Table(name = "transactions")
public class Transactions {
//TODO : Create Swagger Docs
    // POST
    // {
    //   "date": "2025-07-02",
    //   "description": "string",
    //   "createdAt": "2025-07-02T03:48:54.880Z",
    //   "updatedAt": "2025-07-02T03:48:54.880Z"
    // }
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "date",nullable = false, unique = false)
    private LocalDate date;
    @Column(name = "description",nullable = true, unique = false)
    private String description;
    @Column(name = "created_at",nullable = false, unique = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_at",nullable = false, unique = false)
    private LocalDateTime updatedAt;

    public Transactions() {}

    public Transactions(Transactions value) {
        this.id = value.id;
        this.date = value.date;
        this.description = value.description;
        this.createdAt = value.createdAt;
        this.updatedAt = value.updatedAt;
    }

    public Transactions(
        UUID id,
        LocalDate date,
        String description,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
    ) {
        this.id = id;
        this.date = date;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    /**
     * Getter for <code>public.transactions.id</code>.
     */
    public UUID getId() {
        return this.id;
    }

    /**
     * Setter for <code>public.transactions.id</code>.
     */
    public Transactions setId(UUID id) {
        this.id = id;
        return this;
    }

    /**
     * Getter for <code>public.transactions.date</code>.
     */
    public LocalDate getDate() {
        return this.date;
    }

    /**
     * Setter for <code>public.transactions.date</code>.
     */
    public Transactions setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    /**
     * Getter for <code>public.transactions.description</code>.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Setter for <code>public.transactions.description</code>.
     */
    public Transactions setDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * Getter for <code>public.transactions.created_at</code>.
     */
    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    /**
     * Setter for <code>public.transactions.created_at</code>.
     */
    public Transactions setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    /**
     * Getter for <code>public.transactions.updated_at</code>.
     */
    public LocalDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    /**
     * Setter for <code>public.transactions.updated_at</code>.
     */
    public Transactions setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Transactions other = (Transactions) obj;
        if (this.id == null) {
            if (other.id != null)
                return false;
        }
        else if (!this.id.equals(other.id))
            return false;
        if (this.date == null) {
            if (other.date != null)
                return false;
        }
        else if (!this.date.equals(other.date))
            return false;
        if (this.description == null) {
            if (other.description != null)
                return false;
        }
        else if (!this.description.equals(other.description))
            return false;
        if (this.createdAt == null) {
            if (other.createdAt != null)
                return false;
        }
        else if (!this.createdAt.equals(other.createdAt))
            return false;
        if (this.updatedAt == null) {
            if (other.updatedAt != null)
                return false;
        }
        else if (!this.updatedAt.equals(other.updatedAt))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        result = prime * result + ((this.date == null) ? 0 : this.date.hashCode());
        result = prime * result + ((this.description == null) ? 0 : this.description.hashCode());
        result = prime * result + ((this.createdAt == null) ? 0 : this.createdAt.hashCode());
        result = prime * result + ((this.updatedAt == null) ? 0 : this.updatedAt.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Transactions (");

        sb.append(id);
        sb.append(", ").append(date);
        sb.append(", ").append(description);
        sb.append(", ").append(createdAt);
        sb.append(", ").append(updatedAt);

        sb.append(")");
        return sb.toString();
    }
}
