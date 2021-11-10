package me.dio.rodolfohok.saladereuniao.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "meet_in_groups")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Room {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "date", nullable = false)
  private LocalDate date;

  @Column(name = "start_hour", nullable = false)
  private LocalTime startHour;

  @Column(name = "end_hour")
  private LocalTime endHour;

  @Override
  public String toString() {
    return "Room [" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", date=" + date +
        ", startHour=" + startHour +
        ", endHour=" + endHour +
        ']';
  }
}
