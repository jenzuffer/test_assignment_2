# reflections

##computer mouse: idenfity the types of testing you would perform on a computer mouse, to make sure
##that it is of the highest uquality:
a computer mouse is a device driver and hence testing ring zero may be important to ensure that the mouse 
cant be exploited in unexpected ways and potentially be a harm for the remaining of ring 0 components. since
its such a low level component extremely carefull precision has to be made as hardware components wont get easy 
updates unlike software. otherwise you need some specialist for both hardware and software. and general users testing the mouse to report unexpected behaviour.

##Catastrophic failure: Find a story where a software system defect had a bad outcome. Describe what happened. Can you
## idenfity a test that would have prevent it?
some generic issues i have experienced over the years on dedicated machines include:
firewall public zones miss-configuration followed by restarting the firewall locking everybody
 who is not localhost out. Normally you dont test firewalls or have them in applications but
 just the ability to isolated test firewalls before they are reloaded in a real production environment 
 can be very important.
 Other generic errors are addons/scripts/extensions logging information but then accidently the 
 logging becomes way more than expected writing massive log files filling many
 GB of size. This can flood the system by eating all space away and does in most cases when it happens
 also eat a lot of the performance the machine has. I cant really imagine many ways this can be tested
 except of trying to run a method many thousand times in a single second. That would however not be about
 testing the method in itself but a question about if users potentially could spam the logs.

# two katas
##string utility
maven project -> StringUtelizer.java and TestStringUtelizer.java

#investigation of tools junit 5:
explain following tags:
@Tag: is an interface used to declare a tag for an annotatted test class.
@Tag is used to target which tests should be executed in what environment. Tests being dependent on
the environment however does not seem good to begin with. Tests should be inpdendent of the environment.

@Disabled: interface that signals the annotated test class is disabled and should not be executed. Usefull
to temporarily disable code, but dangerous/risky to use in practise. It also may ruin the purpose of why a 
test is present to begin with. Or in this case a test should be refactored instead.  

@RepeatedTest: interface signals annotated method that should be repeated X amount of times.
can test scaleability/performance as well as state/mutability and if they were to interfere after an
X amount of uses. 

@BeforeEach and @AfterEach: interface that signals annotated method should be executed before and After
 each @Test in the the test class. This may be relevant for helper methods. Also helps at setting up
 priorly required conditions or states for the tests to operate with.
 
 @BeforeAll, @AfterAll: interface signals annotated method to be executed before and after all tests 
 in test class. May be helper methods that need to achieve a specific state after running all tests.
 
 @DisplayName: interface used to declare custom display names for annotated test classes/methods.
 typically test reporting in build tools and ide's. 
 
 @Nested: interface? allows inner classes so that you can group several test class inside same parent. 
 It may be usefull for avoiding running multiple tests but i cant see the usefullness in this if its 
 only about testing further tests in one go instead of splitting them. 
 
 @assumeFalse and @assumeTrue: part of the Assumptions class. is collection of util methods that 
 support conditional test execution. Unlike assetions does assumptions cause a test to be aborted instead
 of failed. Usually used when dealing with environment issues. May be usefull and contradicting at the same
 time since it prevents failed tests from causing the overall running tests to fail. 
 
 ##Mocking framework 
 ##Mockito and EasyMock
 ###what are their similarities?
 Mockito uses @Mock annotation aswell as @InjectMocks. EasyMock uses @Mock annotation aswell.
 Both frameworks also appear very similiar in mocking exceptions. Both have support for partial mocking
 However Mockito seems to have an easier approach because EasyMock requires you to define which methods
 will be mocked when creating the mock.
 
 ###What are their differences?
 EasyMock requires @TestSubject annotation to determine what to test. EasyMock also requires 
 EasyMock.replay(mock) to be called for execution of mockings. 
 Mockito has the ability to verify zero interaction with components while easymock does not appear
 to have this capability. 
 
 ### Which one would you prefer, if any, and why?
 probably mockito seems a bit easier but in general i would be satiesfied with the jupiter testing units 
 instead of applying mocking frameworks. One can argue you want to mock databases but then you
 have to treat data as something temeporarily however in the end you want the data stored permanently
 and hence one will experience differences between the mocked environment setup and how the environment will
 behave when data does not get deleted temporarily but instead is stored permanently. 
 One could potentially test mocking of a database setup without ever paying attention for example
 to consistency of primary keys because they always would get reset. Or experience how a database might
 function in case that it is expected to grow significantly in size. 
 