package com.finexus.automation;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.Status;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.IndexDiff.StageState;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

public class GitCloning {

	public static void main(String[] args) throws GitAPIException, IOException {
			/*
			 * Git.cloneRepository() .setURI("https://github.com/eclipse/jgit.git")
			 * .setDirectory(new File("/path/to/repo")) .call();
			 */
			
//			
//			FileRepositoryBuilder repositoryBuilder = new FileRepositoryBuilder();
//			Repository repository = repositoryBuilder.setGitDir(new File("/path/to/repo/.git"))
//			                .readEnvironment() // scan environment GIT_* variables
//			                .findGitDir() // scan up the file system tree
//			                .setMustExist(true)
//			                .build();
////			
//			FileRepositoryBuilder repositoryBuilder = new FileRepositoryBuilder();
//			FileRepositoryBuilder repository = repositoryBuilder.setGitDir(new File("/path/to/repo/.git"));
//			
			//Git git = Git
					   //.cloneRepository()
					  //.setURI( "https://github.com/daskhatri/code.git" ).call();

		// Open an existing repository
//		Repository existingRepo = new FileRepositoryBuilder()
//		    .setGitDir(new File("code/.git"))
//		    .build();

		if( new File( "/code/.git" ).exists()) {
			Git git = Git.open( new File( "d:/mirtalk-ws-eclipse/TestAutomationServer/code/.git" ));
			//Status status = git.status().call();
			  Status status = git.status().call();
			  
		        if (!status.isClean()) {
		            git.add().addFilepattern(".").call();
		            git.commit().setMessage("bs hane commit kr nakhra na kr").call();
		            
		            CredentialsProvider cp = new UsernamePasswordCredentialsProvider("daskhatri", "github#786");
		            git.push().setCredentialsProvider(cp).call();
		        }
			  
	            Set<String> conflicting = status.getConflicting();
	            for (String conflict : conflicting) {
	                System.out.println("Conflicting: " + conflict);
	            }
	            Set<String> added = status.getAdded();
	            for (String add : added) {
	                System.out.println("Added: " + add);
	            }
	            Set<String> changed = status.getChanged();
	            for (String change : changed) {
	                System.out.println("Change: " + change);
	            }
	            Set<String> missing = status.getMissing();
	            for (String miss : missing) {
	                System.out.println("Missing: " + miss);
	            }
	            Set<String> modified = status.getModified();
	            for (String modify : modified) {
	                System.out.println("Modification: " + modify);
	            }
	            Set<String> removed = status.getRemoved();
	            for (String remove : removed) {
	                System.out.println("Removed: " + remove);
	            }
	            Set<String> uncommittedChanges = status.getUncommittedChanges();
	            for (String uncommitted : uncommittedChanges) {
	                System.out.println("Uncommitted: " + uncommitted);
	            }
	            Set<String> untracked = status.getUntracked();
	            for (String untrack : untracked) {
	                System.out.println("Untracked: " + untrack);
	            }
	            Set<String> untrackedFolders = status.getUntrackedFolders();
	            for (String untrack : untrackedFolders) {
	                System.out.println("Untracked Folder: " + untrack);
	            }
	            Map<String, StageState> conflictingStageState = status.getConflictingStageState();
	            for (Map.Entry<String, StageState> entry : conflictingStageState.entrySet()) {
	                System.out.println("ConflictingState: " + entry);
	            }	            
	    	
		}
		
		
		
					
			
	}

}
