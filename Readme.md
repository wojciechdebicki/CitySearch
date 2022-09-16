Improvements:

1. UI - Search header should be a toolbar not just textview
2. Button has wrong style
3. Custom sorting should be refactored to pure comparable solution
4. There is a hardcoded value for United States for filtering - should be injected / passed from
   external class
5. Error handling for retrofit - using pure objects is not such good idea, in production code I
   would like to use
   ~Result pattern
6. In production app we should use atomic design so we should have components library with
   corresponding styles / custom views