package com.helpix.dto.listings;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
@Builder
public class ImageDto {

    private	String	imageUrl;
    private	boolean	isPrimary;
}
