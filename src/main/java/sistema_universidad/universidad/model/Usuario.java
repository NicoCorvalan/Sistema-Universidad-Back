 package sistema_universidad.universidad.model;


 import jakarta.persistence.Column;
 import jakarta.persistence.Entity;
 import jakarta.persistence.GeneratedValue;
 import jakarta.persistence.GenerationType;
 import jakarta.persistence.Id;
 import jakarta.persistence.Table;
 import lombok.*;

 @AllArgsConstructor
 @NoArgsConstructor
 @Getter
 @Setter
 @ToString
 @Entity
 @Table(name = "usuario")
 public class Usuario {
     @Id
     @GeneratedValue(strategy=GenerationType.IDENTITY)
     private Long id;

     @Column
     private String nombre;

     @Column(unique = true)
     private String correo;

     @Column
     private String password;
 }