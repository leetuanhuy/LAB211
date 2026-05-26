/*
 * Class MemberService - Member-related business logic
 */
package Service;

import Model.Member;
import java.util.List;

/**
 * @author Admin
 */
public class MemberService {

    // Check if member ID is valid
    public boolean isValidMemberId(String memberId) {
        return memberId != null && !memberId.trim().isEmpty();
    }

    // Check if email is valid
    public boolean isValidEmail(String email) {
        return email != null && email.contains("@") && email.contains(".");
    }

    // Check phone number validity
    public boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber != null && phoneNumber.matches("\\d{10,11}");
    }

    // Calculate total borrowed books for a member
    public int getTotalBorrowedBooks(Member member) {
        return member.getBorrowedBooks().size();
    }

    // Check if member has borrowed books
    public boolean hasBorrowedBooks(Member member) {
        return !member.getBorrowedBooks().isEmpty();
    }
}

