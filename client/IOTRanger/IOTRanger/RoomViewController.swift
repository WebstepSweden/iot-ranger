import UIKit
import Alamofire


class RoomViewController: UIViewController, UITextFieldDelegate, UITableViewDataSource {
    @IBOutlet weak var samplesTableview: UITableView!
    
    public var roomName:String?
    var textField:UITextField?
    var samplesArray: [Dictionary<String, AnyObject>]! = []
    
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
            self.textField?.delegate = self
            textField?.contentHorizontalAlignment = UIControlContentHorizontalAlignment.center
        } else {
            self.title =  roomName
            self.fetchSamples()
        }
        
        self.samplesTableview.dataSource = self
    }
    
    @IBAction func tapSensorButton(_ sender: UIButton) {
        sender.isUserInteractionEnabled = false
        sender.alpha = 0.5
        UIView.animate(withDuration: 0.5, animations: { 
            sender.alpha = 1
        }) { (finished) in
            sender.isUserInteractionEnabled = true
            self.fetchSamples()
        }
        
        print("tapping")
        self.registerTap()
    }
    
    
    override func viewDidAppear(_ animated: Bool) {
        print("textfield frame: \(self.textField?.frame)")
    }

    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        if textField.text != nil {
            self.roomName = textField.text
            self.title = self.roomName
            self.registerRoom(roomName: self.roomName!)
            var titleview = UILabel.init(frame: CGRect(x: 0, y: 0, width: 1000, height: 21))
            titleview.text = self.roomName
            titleview.textAlignment = NSTextAlignment.center
            self.navigationItem.titleView = titleview
            return true
        } else {
            return false
        }
    }

    func registerRoom(roomName:String) {
        print("register roomname")
        Alamofire.request("http://\(Constants.ipAddress):8080/ranger/location?name=\(roomName)", method: .put).responseJSON { (response) in
            print(response.request ?? "no request data")
            self.textField?.removeFromSuperview()
        }
    }
    
    func registerTap() {
        //localhost:8080/ranger/register?id=123&timestamp=2017-04-08T10:11:12.123&location=Room+3
        print("http://\(Constants.ipAddress):8080/ranger/register?id=\(Constants.sensorId)&timestamp=\(Date().iso8601)&location=\(self.roomName!)")
        
        var url = "http://\(Constants.ipAddress):8080/ranger/register?id=\(Constants.sensorId)&timestamp=\(Date().iso8601)&location=\(self.roomName!)"
        url = url.replacingOccurrences(of: " ", with: "+")
        print(url)
        Alamofire.request(url, method: .put).responseJSON { (response) in
            print(response.error)
            print(response.request ?? "no request data")
            self.textField?.removeFromSuperview()
        }

    }
    
    func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return self.samplesArray.count
    }
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = UITableViewCell.init()
        let sampleDict = self.samplesArray[indexPath.row]
        let timestamp = sampleDict["timestamp"] as? String
        let inRange = sampleDict["inRange"] as? Bool
        cell.textLabel?.text = timestamp
        if inRange! {
            cell.backgroundColor = UIColor(red: 0.5, green: 1.0, blue: 0.5, alpha: 1.0)
        }
        return cell
    }
    
    func fetchSamples() {
        print("http://\(Constants.ipAddress):8080/ranger/register?id=\(Constants.sensorId)&timestamp=\(Date().iso8601)&location=\(self.roomName!)")

        
        var url = "http://\(Constants.ipAddress):8080/ranger/location?location=\(self.roomName!)"
        url = url.replacingOccurrences(of: " ", with: "+")
        print(url)
        Alamofire.request(url, method: .get).responseJSON { (response) in
            var samples:[Dictionary<String,String>]! = []
            
            let responseDictionary = response.result.value as?  Dictionary<String, AnyObject>
            let samplesDictionaryArray = responseDictionary?["registrations"]  as? [Dictionary<String, AnyObject>]
            if samples != nil{
                self.samplesArray = samplesDictionaryArray!
            }
            self.samplesTableview.reloadData()
        }
    }
    
    /*
    // MARK: - Navigation localhost:8080/ranger/register?id=123&timestamp=2017-04-08T10:11:12.123&location=Room+3

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}

extension Formatter {
    static let iso8601: DateFormatter = {
        let formatter = DateFormatter()
        formatter.calendar = Calendar(identifier: .iso8601)
        formatter.locale = Locale(identifier: "en_US_POSIX")
        formatter.timeZone = TimeZone(secondsFromGMT: 0)
        formatter.dateFormat = "yyyy-MM-dd'T'HH:mm:ss.SSSXXXXX"
        return formatter
    }()
}
extension Date {
    var iso8601: String {
        return Formatter.iso8601.string(from: self)
    }
}

extension String {
    var dateFromISO8601: Date? {
        return Formatter.iso8601.date(from: self)   // "Mar 22, 2017, 10:22 AM"
    }
}
