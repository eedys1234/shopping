package web.dto;

import domain.Address;
import domain.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberRequestDto {

    private String name;
    private String city;
    private String street;
    private String zipcode;


    public Member toEntity() {

        Address address = Address.builder()
                .city(city)
                .street(street)
                .zipcode(zipcode)
                .build()
        ;

        return Member.builder()
                .name(name)
                .address(address)
                .build()
        ;
    }


}
