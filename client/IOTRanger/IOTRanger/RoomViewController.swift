import UIKit

class RoomViewController: UIViewController {
    
    public var roomName:String?
    var textField:UITextField?
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        if (roomName == nil) {
            self.textField = UITextField.init(frame: CGRect(x: 0, y: 0, width: 1000, height: 21))
            self.textField?.backgroundColor = UIColor.white
            self.navigationItem.titleView = textField;
            self.textField?.becomeFirstResponder()
        } else {
            self.title =  roomName
        }
    }
    
    override func viewDidAppear(_ animated: Bool) {
        print("textfield frame: \(self.textField?.frame)")
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}
