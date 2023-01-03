import { Pageable } from './pageable.model';
import { Sort } from './sort.model';

export interface Page<T> {
    content: T[];
    empty?: boolean;
    first?: boolean;
    last?: boolean;
    numberOfElements: number;
    pageable?: string;
    size: number;
    // eslint-disable-next-line id-blacklist
    number: number;
    sort?: Sort;
    totalElements: number;
    totalPages: number;
}
