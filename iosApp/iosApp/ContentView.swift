import SwiftUI
import shared


struct ContentView: View {
    
    private var viewModel: MainViewModel
    
    init() {
        viewModel = MainViewModel()
        viewModel.pageData.addObserver{(v) in
            if (result is Response) {
                let response = result as! Response
                let a = 0;
            }
        }
    }
    
    var body: some View {
        Text("greet()")
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
