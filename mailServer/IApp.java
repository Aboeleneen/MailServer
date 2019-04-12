package eg.edu.alexu.csd.datastructure.mailServer;

import java.io.IOException;

import eg.edu.alexu.csd.datastructure.linkedList.ILinkedList;
import eg.edu.alexu.csd.datastructure.mailServer.ISort;
import org.json.simple.parser.ParseException;

public interface IApp {
	/**
	 * * Sign in to the application * @param email * @param password * @return
	 * false if the email name not exist
	 * @throws IOException 
	 */
	public boolean signin(String email, String password) throws IOException;

	/**
	 * * Create new account * @param contact * @return false if the email name
	 * already exist
	 * @throws IOException 
	 */
	public boolean signup(IContact contact) throws IOException;

	/**
	 * * This function should be called before reading from the index file * and
	 * apply the sort and search parameters * @param folder currently shown, can
	 * be null * @param filter to apply search, can be null * @param sort to
	 * apply sort
	 */
public void setViewingOptions(IFolder folder, IFilter filter, ISort sort) throws IOException, ParseException ;
	/**
	 * * You should use setViewingOptions function first* @param page to handle
	 * paging * @return list of emails
	 */
	public IMail[] listEmails(int page);

	/**
	 * * You should use setViewingOptions function first * @param mails to be
	 * moved to trash
	 */
	public void deleteEmails(ILinkedList mails);

	/**
	 * * You should use setViewingOptions function first * @param mails to be
	 * moved * @param des the destination folder
	 */
	public void moveEmails(ILinkedList mails, IFolder des);

	/**
	 * * Send a new email * @param email should contain all the information
	 * needed * sender, list of receivers, list of attachments, email body, ...
	 * * @return false if something wrong happened like sending to non-existing
	 * user.
	 * @throws IOException 
	 */
	public boolean compose(IMail email) throws IOException, ParseException;
}