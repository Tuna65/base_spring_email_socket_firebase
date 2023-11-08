package com.example.base.dto;

import com.example.base.models.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseDTO implements Serializable {

  //  @Schema(hidden = true)
  private Date createdAt;

  //  @Schema(hidden = true)
  private Date updatedAt;

  //  @Schema(hidden = true)
  private Long createdBy;

  //  @Schema(hidden = true)
  private Long updatedBy;

  public BaseDTO(BaseEntity e) {
    this.createdAt = e.getCreatedAt();
    this.createdBy = e.getCreatedBy();
    this.updatedAt = e.getUpdatedAt();
    this.updatedBy = e.getUpdatedBy();
  }
}