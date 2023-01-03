import { Sort } from './sort.model';

export interface Pageable {
    sort?: string;
    pageNumber: number;
    pageSize: number;
    offset?: number;
    paged?: boolean;
    unpaged?: boolean;
}
