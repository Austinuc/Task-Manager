package com.oasis.taskmanagement.repositories.custom;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

/**
 * <p>This adds global capabilities to all Repository extending it. In this case, I'm using it to add control over the maximum
 *  result returned when using dynamic query implementation from the JPA Specification API. {JpaSpecificationExecutor<T>}</p>
 *</p>
 * <p>
 * This same functionality is easily achieved with static named query and methodNamedQuery.
 * However, I'm building the query dynamically based on some input parameters, and I'm using
 * the JPA Specification implementation for this purpose. Currently, with this approach, we can achieve our desired goal
 * of controlling the maximum results returned from our dynamic query by using the `the findAll(Specification, Pageable) method.`
 *
 * But this comes with a little overhead we want to avoid. This solution was found on
 * GitHub: <a href="https://gist.github.com/tcollins/0ebd1dfa78028ecdef0b">https://gist.github.com/tcollins/0ebd1dfa78028ecdef0b</a>
 *</p>
 * @Usage:
 * <ul>
 *     <li>
 *         `@Repository
 * public interface MyRepository extends BaseRepository<T, ID>, JpaSpecificationExecutor<T> {
 * }
 *     </li>
 * </ul>
 *
 */
@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {
    List<T> findAll(Specification<T> spec, long offset, int maxResults, Sort sort);

    List<T> findAll(Specification<T> spec, long offset, int maxResults);
}
