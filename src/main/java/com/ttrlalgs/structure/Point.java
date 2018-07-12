package com.ttrlalgs.structure;

import lombok.*;
import lombok.experimental.FieldDefaults;

@RequiredArgsConstructor(staticName = "of")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@EqualsAndHashCode
@Getter
@ToString
public class Point {
    double x, y;
}
