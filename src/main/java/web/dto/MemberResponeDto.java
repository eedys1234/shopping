package web.dto;

import domain.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberResponeDto {

    private Long id;
    private String name;
    private String city;
    private String street;
    private String zipcode;

    public MemberResponeDto(Member member) {
        this.id = member.getId();
        this.name = member.getName();
        this.city = member.getAddress().getCity();;
        this.street = member.getAddress().getStreet();
        this.zipcode = member.getAddress().getZipcode();
    }

}
