package main.java.ie.bibliotech;
import main.java.ie.bibliotech.model.Loan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
public interface LoanRepository extends JpaRepository<Loan, Long> {
}
