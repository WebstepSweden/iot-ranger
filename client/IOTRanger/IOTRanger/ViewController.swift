import UIKit

class ViewController: UIViewController, UITableViewDataSource, UITableViewDelegate {
    @IBOutlet weak var roomsTableView: UITableView!
    var roomNames = [String]()

    override func viewDidLoad() {
        super.viewDidLoad()
        self.title = "Sensor: 206881543"
        self.navigationItem.rightBarButtonItem = UIBarButtonItem.init(title: "select", style: UIBarButtonItemStyle.plain , target: self, action: #selector(self.selectSensor))
        self.roomsTableView.dataSource = self
        self.roomsTableView.delegate = self
        self.roomNames = ["room 1", "room 2", "room 3", "room 4", "room A", "room B", "room C", "room D", "room AND", "room THEN", "room SOME", "room MORE", "room NAMES", "room"]
    }

    func selectSensor (){
        self.present(UIAlertController.init(title: "Ha Ha!", message: "This feature is not implemented", preferredStyle: UIAlertControllerStyle.alert), animated: true, completion: nil)
        let deadlineTime = DispatchTime.now() + .seconds(1)
        DispatchQueue.main.asyncAfter(deadline: deadlineTime) { 
            self.presentedViewController?.dismiss(animated: true, completion: nil)
        }
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

}

