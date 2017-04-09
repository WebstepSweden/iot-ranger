import UIKit
import Alamofire

struct Constants {
    static let ipAddress = "10.47.54.248"//"10.47.52.99"//
}

class ViewController: UIViewController, UITableViewDataSource, UITableViewDelegate {
    @IBOutlet weak var roomsTableView: UITableView!
    var roomNames = [String]()

    override func viewDidLoad() {
        super.viewDidLoad()
        self.title = "Sensor: 206881543"
        self.navigationItem.rightBarButtonItem = UIBarButtonItem.init(title: "select", style: UIBarButtonItemStyle.plain , target: self, action: #selector(self.selectSensor))
        self.roomsTableView.dataSource = self
        self.roomsTableView.delegate = self
        self.roomNames = []
    }

    func selectSensor (){
        self.present(UIAlertController.init(title: "Ha Ha!", message: "This feature is not implemented", preferredStyle: UIAlertControllerStyle.alert), animated: true, completion: nil)
        let deadlineTime = DispatchTime.now() + .seconds(1)
        DispatchQueue.main.asyncAfter(deadline: deadlineTime) { 
            self.presentedViewController?.dismiss(animated: true, completion: nil)
        }
        let diceRoll = Int(arc4random_uniform(10000000))
        self.getRooms()
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        let roomName = roomNames[indexPath.row]
        print("Selected room: \(roomName)")
        let storyboard = UIStoryboard(name: "Main", bundle: nil)
        let controller:RoomViewController = storyboard.instantiateViewController(withIdentifier: "RoomViewController") as! RoomViewController
        controller.roomName = roomName
        self.show(controller, sender: nil)
    }
    
    // tableview Datasouce functions
    func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return roomNames.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = UITableViewCell.init(style: UITableViewCellStyle.default, reuseIdentifier: "nuttin")
        cell.textLabel?.text = roomNames[indexPath.row]
        return cell
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        switch segue.identifier {
        case "showRoomViewControllerSegue"?:
            print("show room view controller")
        default:
            print("this is not my segue")
        }
    }
    

    
    func getRooms() {
        Alamofire.request("http://\(Constants.ipAddress):8080/ranger/locations", method: .get).responseJSON { (response) in
            var rooms:[String]! = []
            let responseArray = response.result.value as?  [Dictionary<String, AnyObject>]
            if responseArray != nil {
                for object in responseArray!{
                    let roomName = object["name"] as? String
                    if roomName != nil{
                        rooms.append(roomName!)
                    }
                }
            }
            self.roomNames = rooms
            self.roomsTableView.reloadData()
        }
    }

}

