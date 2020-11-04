package com.feedback.vlearning.branch;

import com.feedback.vlearning.user.Status;
import com.feedback.vlearning.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BranchRepository extends JpaRepository<Branch,Long> {

    @Query("select b from Branch b where b.branchName=?1 and b.status=?2")
    Branch findByBranchNameAndStatus(String name , Status status);

    Branch findByUserAndStatus(User user, Status status);

    List<Branch> findByStatus(Status status);

    @Query("from Branch b where b.id=?1")
    Branch findBranchById(Long id);

}
